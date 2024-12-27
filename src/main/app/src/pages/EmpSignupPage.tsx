import axios from "axios";
import {useForm} from "react-hook-form";
import {useEffect, useState} from "react";
import {API_URL} from "../constants/constants.ts";

const EmpSignupPage = () => {

    const {
        register,
        handleSubmit,
        reset,
    } = useForm<EmpSignupForm>();

    const [departments, setDepartments] = useState<Department[]>([]);
    const [empPoses, setEmpPoses] = useState<CommonCode[]>([]);
    const [suggestedRomanizedName, setSuggestedRomanizedName] = useState<string[]>([]);

    useEffect(() => {
        getDepartments();
        getEmpPosCd();
    }, []);

    return (
        <div>
            <form onSubmit={handleSubmit(onSubmit)} onKeyDown={(e) => e.key === 'Enter' && e.preventDefault()}>
                <div className="form-group">
                    <label htmlFor="empNo">사번:</label>
                    <input
                        {...register("empNo")}
                        type={"text"}
                        className={"form-control"}
                        max={5}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="empNm">사원명:</label>
                    <input
                        {...register("empNm")}
                        type={"text"}
                        className={"form-control"}
                        onBlur={(e) => getRomanizeKorName(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="empNmEn">사원명(영문):</label>
                    <select {...register("empNmEn")} className={"form-control"}>
                        <option>선택</option>
                        {suggestedRomanizedName && suggestedRomanizedName.map((item, index) =>
                            <option key={index} value={item}>{item}</option>
                        )}
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="deptCd">부서:</label>
                    <select {...register("deptCd")} className={"form-control"}>
                        <option>선택</option>
                        {departments && departments.map((dept, index) =>
                            <option key={index} value={dept.deptCd}>{dept.deptNm}</option>
                        )}
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="empPosCd">직급:</label>
                    <select {...register("empPosCd")} className={"form-control"}>
                        <option>선택</option>
                        {empPoses && empPoses.map((pos, index) =>
                            <option key={index} value={pos.cmCd}>{pos.cmNm}</option>
                        )}
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="hp">핸드폰:</label>
                    <input
                        {...register("hp")}
                        type={"text"}
                        className={"form-control"}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="tel">내선전화:</label>
                    <input
                        {...register("tel")}
                        type={"text"}
                        className={"form-control"}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="email">이메일:</label>
                    <input
                        {...register("email")}
                        type={"text"}
                        className={"form-control"}
                    />
                </div>

                <div className="form-group">
                    <button type="submit" className="btn btn-lg btn-block btn-primary">
                        등록
                    </button>
                </div>
            </form>
        </div>
    );

    function onSubmit(data: EmpSignupForm) {
        console.log(data);
        axios.post(`${API_URL}/api/emp`, data)
            .then(() => {
                alert("임직원 등록이 완료 되었습니다 :)");
                reset();
            })
            .catch(() => {
                alert("임직원 등록에 실패했습니다 :(\n\n다시 시도해 주세요");
            });
    }

    function getEmpPosCd() {
        axios.get(`${API_URL}/api/emp/empPoses`)
            .then((poses) => {
                setEmpPoses(poses.data);
            });
    }

    function getDepartments() {
        axios.get(`${API_URL}/api/emp/depts`)
            .then((depts) => {
                setDepartments(depts.data);
            });
    }

    function getRomanizeKorName(korName: string) {

        axios.get(`${API_URL}/api/emp/romanize`,
            {
                params: {
                    name: korName
                }
            })
            .then((response) => {
                const items = response.data;
                setSuggestedRomanizedName(items);
            });
    }
}

type EmpSignupForm = {
    empNo: string,
    empNm: string,
    empNmEn: string,
    deptCd: string,
    empPosCd: string,
    hp: string,
    tel: string,
    email: string,
}

type Department = {
    deptCd: string,
    deptNm: string,
}

type CommonCode = {
    cmCd: string,
    cmNm: string,
    cmNmEn: string,
}

export default EmpSignupPage;