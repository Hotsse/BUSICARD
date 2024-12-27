import {useNavigate} from "react-router";

const DashboardPage = () => {

    const navigate = useNavigate();

    return (
        <div className={"dashboard"}>
            <button type="button" className={"btn btn-primary"} onClick={() => navigate('/emp/signup')}>
                임직원 등록
            </button>

            <button type="button" className={"btn btn-primary"} onClick={() => navigate('/busicard')}>
                명함 다운로드
            </button>

            <button type="button" className={"btn btn-primary"} onClick={() => navigate('/busicard/parse')}>
                명함 이미지<br/>
                자동분석
            </button>
        </div>
    );
}

export default DashboardPage;