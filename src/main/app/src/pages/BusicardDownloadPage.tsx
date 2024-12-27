import {useState} from "react";
import {API_URL} from "../constants/constants.ts";

const BusicardDownloadPage = () => {

    const [imageKoSrc, setImageKoSrc] = useState<string>("");
    const [imageEnSrc, setImageEnSrc] = useState<string>("");

    return (
        <div className={"busicard-download"}>
            <div className={"form-group"}>
                <label htmlFor="empNo">사번:</label>
                <input
                    type={"text"}
                    className={"form-control"}
                    max={"5"}
                    onBlur={(e) => {
                        const empNo = e.target.value;
                        setImageKoSrc(`${API_URL}/api/busicard/download/ko?empNo=${empNo}`);
                        setImageEnSrc(`${API_URL}/api/busicard/download/en?empNo=${empNo}`);
                    }}
                />
            </div>

            <div className={"form-group"}>
                <img src={imageKoSrc} className={"busicard-img img-responsive m-t-sm"} />
                <img src={imageEnSrc} className={"busicard-img img-responsive m-t-sm"} />
            </div>
        </div>
    );
}

export default BusicardDownloadPage;