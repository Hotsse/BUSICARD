package com.hotsse.busicard;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotsse.busicard.api.card.constants.CardTypeEnum;
import com.hotsse.busicard.api.emp.service.EmployeeService;
import com.hotsse.busicard.api.emp.vo.DeptVO;
import com.hotsse.busicard.api.emp.vo.EmployeeVO;

@SpringBootTest
class BusicardApplicationTests {
	
	private final String STORATGE_PATH = "C:/storages/BUSICARD";
	
	@Autowired
	private EmployeeService employeeService;
	
	@Test
	void contextLoads() throws Exception {
		
		String empNo = "19063";
		EmployeeVO emp = this.employeeService.getEmployee(empNo);
		DeptVO dept = this.employeeService.getDept(emp.getDeptCd());
		DeptVO upperDept = null;
		
		if(dept.getUpperDeptCd() != null) {
			upperDept = this.employeeService.getDept(dept.getUpperDeptCd());
		}
		else {
			upperDept = dept;
			dept = null;
		}
		
		this.createImage(CardTypeEnum.KO
				, String.format("%s_%s", emp.getEmpNm(), emp.getEmpNo())
				, emp.getEmpNm()
				, (upperDept != null) ? upperDept.getDeptNm() : ""
				, (dept != null) ? dept.getDeptNm() : ""
				, emp.getEmpPosNm()
				, emp.getTel()
				, emp.getHp()
				, emp.getEmail());
		
		this.createImage(CardTypeEnum.EN
				, String.format("%s_%s", emp.getEmpNm(), emp.getEmpNo())
				, emp.getEmpNmEn()
				, (upperDept != null) ? upperDept.getDeptNmEn() : ""
				, (dept != null) ? dept.getDeptNmEn() : ""
				, emp.getEmpPosNmEn()
				, emp.getTel()
				, emp.getHp()
				, emp.getEmail());
		
	}
	
	private void createImage(CardTypeEnum cardType, String id, String name, String dept1, String dept2, String rank, String tel, String cell, String email) throws Exception {
		
		String filePath = STORATGE_PATH + "/cards/" + id;
		
		try {
			new File(filePath).mkdir();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			BufferedImage cardImg = ImageIO.read(new File(STORATGE_PATH + "/sample/card/card_" + (cardType == CardTypeEnum.KO ? "ko" : "en") + ".png"));
			BufferedImage nameImg = this.convertTextToBufferedImage((cardType == CardTypeEnum.KO ? name.replaceAll(".", "$0 ") : name), new Font("다키 B", Font.PLAIN, 42), Color.BLACK);
			BufferedImage deptImg1 = this.convertTextToBufferedImage(dept1, new Font("다키 M", Font.PLAIN, 18), Color.BLACK);
			BufferedImage deptImg2 = this.convertTextToBufferedImage(dept2, new Font("다키 M", Font.PLAIN, 18), Color.BLACK);
			BufferedImage rankImg = this.convertTextToBufferedImage(rank, new Font("다키 M", Font.PLAIN, 18), Color.BLACK);
			BufferedImage telImg = this.convertTextToBufferedImage(tel, new Font("다키 M", Font.PLAIN, 28), Color.BLACK);
			BufferedImage cellImg = this.convertTextToBufferedImage(cell, new Font("다키 M", Font.PLAIN, 28), Color.BLACK);
			BufferedImage emailImg = this.convertTextToBufferedImage(email, new Font("다키 M", Font.PLAIN, 28), Color.BLACK);
			
			int width = cardImg.getWidth();
			int height = cardImg.getHeight();
			
			BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = (Graphics2D) result.getGraphics();
			
			graphics.setBackground(Color.WHITE);
			graphics.drawImage(cardImg, 0, 0, null);
			
			graphics.drawImage(nameImg, 30, 30, null);
			graphics.drawImage(deptImg1, 35, 100, null);
			graphics.drawImage(deptImg2, 35, 125, null);
			graphics.drawImage(rankImg, 35, 150, null);
			
			graphics.drawImage(telImg, 55, 215, null);
			graphics.drawImage(cellImg, 420, 215, null);
			graphics.drawImage(emailImg, 55, 265, null);
			
			ImageIO.write(result, "png", new File(filePath + "/" + id + "_" + (cardType == CardTypeEnum.KO ? "ko" : "en") + ".png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private BufferedImage convertTextToBufferedImage(String text, Font font, Color color) throws Exception {
		
		BufferedImage image = null;
		
		try {
			image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics2d = image.createGraphics();
			graphics2d.setFont(font);
			FontMetrics fontMetrics = graphics2d.getFontMetrics();
			int width = fontMetrics.stringWidth(text);
			int height = fontMetrics.getHeight();
			graphics2d.dispose();
			
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			graphics2d = image.createGraphics();
			graphics2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			graphics2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			graphics2d.setFont(font);
			fontMetrics = graphics2d.getFontMetrics();
			graphics2d.setColor(color);
			graphics2d.drawString(text, 0, fontMetrics.getAscent());
			graphics2d.dispose();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return image;
	}
}
