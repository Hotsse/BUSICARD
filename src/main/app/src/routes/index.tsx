import {createBrowserRouter} from "react-router-dom";
import DashboardPage from "../pages/DashboardPage.tsx";
import EmpSignupPage from "../pages/EmpSignupPage.tsx";
import BusicardParsePage from "../pages/BusicardParsePage.tsx";
import BusicardDownloadPage from "../pages/BusicardDownloadPage.tsx";

export const router = createBrowserRouter(
    [
        {
            path: "/",
            element: <DashboardPage />
        },
        {
            path: "/emp/signup",
            element: <EmpSignupPage />
        },
        {
            path: "/busicard",
            element: <BusicardDownloadPage />
        },
        {
            path: "/busicard/parse",
            element: <BusicardParsePage />
        },
    ]
);