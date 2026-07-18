import React, { useEffect, useState } from "react";
import { AppBar, Toolbar, Typography, Link } from "@mui/material";
import { useLocation } from "react-router-dom";
import "./styles.css";
import axios from "axios";
import Button from "react-bootstrap/Button";
import useStyles from "./styles";

function TopBar() {
  const classes = useStyles();
  const [appContext, setAppContext] = useState(""); // State to hold app context
  const location = useLocation();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const namePath = location.pathname.slice(0, 7);
        const id = location.pathname.slice(7);
        const response = await axios.get(
          `https://5gz5y6-5000.csb.app/api/user/${id}`,
        );
        const user = response.data;
        const fullname = ` ${user.last_name}`;
        if (location.pathname === "/") {
          setAppContext("Home");
        } else if (location.pathname.includes("/users/")) {
          setAppContext(`Infomation of User ${fullname}`);
        } else if (namePath === "/photos") {
          setAppContext(`Photos of User ${fullname}`);
        } else {
          setAppContext("");
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [location]);

  const handleLogout = () => {
    localStorage.removeItem("authToken");
    window.location.reload();
  };
  const [UserName, setUserName] = useState("");

  useEffect(() => {
    const token = localStorage.getItem("authToken");
    const fetchUser = async () => {
      try {
        const response = await axios.get(
          `https://5gz5y6-5000.csb.app/api/auth`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          },
        );
        if (response.data.success) {
          setUserName(response.data.UserName);
        }
      } catch (error) {}
    };
    fetchUser();
  }, []);
  return (
    <AppBar className="topbar-appBar" position="absolute">
      <Toolbar className={classes.fab1}>
        <Typography variant="h6" color="inherit" className="app-context">
          {appContext ? `${appContext}` : "Home"}
        </Typography>
        <Typography variant="h6" color="inherit" className="app-context">
          {UserName}
        </Typography>
        <Button
          onClick={handleLogout}
          variant="light"
          style={{ float: "right", right: "10px" }}
        >
          Log Out
        </Button>{" "}
      </Toolbar>
    </AppBar>
  );
}

export default TopBar;
