import {useState} from "react";
import axios from "axios";
import {API_URL} from "../constants/constants.ts";

const BusicardParsePage = () => {

    const [selectedFile, setSelectedFile] = useState<File | null>(null);

    return (
        <div>
            <form>
                <div className={"form-group"}>
                    <label htmlFor="empNo">명함:</label>
                    <input
                        type={"file"}
                        className={"form-control"}
                        onChange={(e) => setSelectedFile(e.target.files && e.target.files[0])}
                    />
                </div>
                <div className={"form-group"}>
                    <button type={"button"} className={"btn btn-block btn-primary"} onClick={uploadFile}>
                        업로드
                    </button>
                </div>
            </form>
        </div>
    );

    function uploadFile() {

        if(!selectedFile) {
            alert("파일을 첨부하고 시도해 주세요.");
            return;
        }

        const formData = new FormData();
        formData.append("uploadFile", selectedFile);

        axios.post(`${API_URL}/api/busicard/parse`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        })
            .then((response) => {
                const data = response.data;

                if(data.empNm) {
                    alert(`이름 : ${data.empNm}\n직급 : ${data.empPosNm}\n내선전화 : ${data.tel}\n핸드폰 : ${data.hp}\n이메일 : ${data.email}`);
                }
                else if(data.empNmEn) {
                    alert(`이름 : ${data.empNmEn}\n직급 : ${data.empPosNmEn}\n내선전화 : ${data.tel}\n핸드폰 : ${data.hp}\n이메일 : ${data.email}`);
                }
                else {
                    alert("명함 분석에 실패했습니다.");
                }
            });
    }
}

export default BusicardParsePage;