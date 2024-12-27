import "./App.css";
import {RouterProvider} from "react-router";
import {router} from "./routes";

const App = () => {

    return (
        <>
            <div className={"jumbotron text-center"} onClick={clickLogo}>
                <h1>BUSICARD</h1>
                <p>명함 관리 파일럿 프로젝트</p>
            </div>

            <div className={"container"}>
                <RouterProvider router={router}/>
            </div>
        </>
    );

    function clickLogo() {
        window.location.href = '/';
    }
}

export default App
