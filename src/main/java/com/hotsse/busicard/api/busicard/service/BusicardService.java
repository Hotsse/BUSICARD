package com.hotsse.busicard.api.busicard.service;

import com.hotsse.busicard.api.busicard.constants.CardTypeEnum;
import com.hotsse.busicard.api.emp.service.EmployeeService;
import com.hotsse.busicard.api.emp.vo.DeptVO;
import com.hotsse.busicard.api.emp.vo.EmployeeVO;
import com.hotsse.busicard.trdparty.naver.ocr.service.OcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class BusicardService {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private OcrService ocrService;

	private static String KOREAN_FONT = "Nanum Gothic";

	public BufferedImage createBusicard(String empNo, CardTypeEnum cardType, HttpServletResponse res) throws Exception {

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

		res.setContentType(MediaType.IMAGE_PNG_VALUE);
		BufferedImage image = null;

		if(cardType == CardTypeEnum.KO) {

			image = this.createImage(CardTypeEnum.KO
					, String.format("%s_%s", emp.getEmpNm(), emp.getEmpNo())
					, emp.getEmpNm()
					, (upperDept != null) ? upperDept.getDeptNm() : ""
					, (dept != null) ? dept.getDeptNm() : ""
					, emp.getEmpPosNm()
					, emp.getTel()
					, emp.getHp()
					, emp.getEmail());
		}
		else if(cardType == CardTypeEnum.EN) {

			image = this.createImage(CardTypeEnum.EN
					, String.format("%s_%s", emp.getEmpNm(), emp.getEmpNo())
					, emp.getEmpNmEn()
					, (upperDept != null) ? upperDept.getDeptNmEn() : " "
					, (dept != null) ? dept.getDeptNmEn() : " "
					, emp.getEmpPosNmEn()
					, emp.getTel()
					, emp.getHp()
					, emp.getEmail());
		}

		return image;
	}
	
	public void downloadBusicard(String empNo, CardTypeEnum cardType, HttpServletResponse res) throws Exception {

		var image = this.createBusicard(empNo, cardType, res);

		if(ObjectUtils.isEmpty(image))  {
			res.setStatus(HttpStatus.NOT_FOUND.value());
			return;
		}

		try {
			ImageIO.write(image, "png", res.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			res.setStatus(HttpStatus.NOT_FOUND.value());
		}
	}
	
	public EmployeeVO parseBusicard(MultipartFile file) throws Exception {
		
		Map<String, Object> resultMap = this.ocrService.parseBusicard(file);
		
		EmployeeVO emp = null;
		if(resultMap.get("이름").toString().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
			emp = EmployeeVO.builder()
					.empNm(resultMap.get("이름").toString())
					.empPosNm(resultMap.get("직급").toString())
					.tel(resultMap.get("사내전화").toString())
					.hp(resultMap.get("핸드폰").toString())
					.email(resultMap.get("이메일").toString())
					.build();
		}
		else {
			emp = EmployeeVO.builder()
					.empNmEn(resultMap.get("이름").toString())
					.empPosNmEn(resultMap.get("직급").toString())
					.tel(resultMap.get("사내전화").toString())
					.hp(resultMap.get("핸드폰").toString())
					.email(resultMap.get("이메일").toString())
					.build();
		}
		
		return emp;
	}
	
	private BufferedImage createImage(CardTypeEnum cardType, String id, String name, String dept1, String dept2, String rank, String tel, String cell, String email) throws Exception {

		try {
			BufferedImage cardImg = ImageIO.read(this.loadFileInputStreamFromResources("sample/card/card_" + (cardType == CardTypeEnum.KO ? "ko" : "en") + ".png"));
			BufferedImage nameImg = this.convertTextToBufferedImage((cardType == CardTypeEnum.KO ? name.replaceAll(".", "$0 ") : name), new Font("다키 B", Font.PLAIN, 42), Color.BLACK);
			BufferedImage deptImg1 = this.convertTextToBufferedImage(dept1, new Font(KOREAN_FONT, Font.PLAIN, 18), Color.BLACK); //한글 지원을 위한 폰트 설정
			BufferedImage deptImg2 = this.convertTextToBufferedImage(dept2, new Font(KOREAN_FONT, Font.PLAIN, 18), Color.BLACK);
			BufferedImage rankImg = this.convertTextToBufferedImage(rank, new Font(KOREAN_FONT, Font.PLAIN, 18), Color.BLACK);
			BufferedImage telImg = this.convertTextToBufferedImage(tel, new Font(KOREAN_FONT, Font.PLAIN, 28), Color.BLACK);
			BufferedImage cellImg = this.convertTextToBufferedImage(cell, new Font(KOREAN_FONT, Font.PLAIN, 28), Color.BLACK);
			BufferedImage emailImg = this.convertTextToBufferedImage(email, new Font(KOREAN_FONT, Font.PLAIN, 28), Color.BLACK);
			
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

			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
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

	public InputStream loadFileInputStreamFromResources(String filePath) throws IOException {
		return new ClassPathResource(filePath).getInputStream();
	}
}
