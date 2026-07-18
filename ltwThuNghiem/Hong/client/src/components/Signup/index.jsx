import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";

function Signup() {
  const [first_name, setFirstName] = useState("");
  const [last_name, setLastName] = useState("");
  const [location, setLocation] = useState("");
  const [description, setDescription] = useState("");
  const [occupation, setOccupation] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleSignup = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "https://5gz5y6-5000.csb.app/api/user/createUser",
        {
          first_name,
          last_name,
          location,
          description,
          occupation,
          username,
          password,
        },
      );

      if (response.status === 200) {
        // Đăng ký thành công, chuyển hướng đến trang đăng nhập
        navigate("/");
      }
    } catch (err) {
      // Xử lý lỗi nếu có
      setError(
        err.response?.data?.message || "An error occurred during signup.",
      );
    }
  };

  return (
    <div className="Auth-form-container">
      <form className="Auth-form" onSubmit={handleSignup}>
        <div className="Auth-form-content">
          <h3 className="Auth-form-title">Signup</h3>
          {error && <div className="error-message">{error}</div>}
          <div className="form-group mt-3">
            <label>First Name</label>
            <input
              type="text"
              className="form-control mt-1"
              placeholder="Enter First Name"
              value={first_name}
              onChange={(e) => setFirstName(e.target.value)}
            />
          </div>
          <div className="form-group mt-3">
            <label>Last Name</label>
            <input
              type="text"
              className="form-control mt-1"
              placeholder="Enter Last Name"
              value={last_name}
              onChange={(e) => setLastName(e.target.value)}
            />
          </div>
          <div class="form-group mt-3">
            <label>Location</label>
            <input
              type="text"
              className="form-control mt-1"
              placeholder="Enter Location"
              value={location}
              onChange={(e) => setLocation(e.target.value)}
            />
          </div>
          <div className="form-group mt-3">
            <label>Description</label>
            <input
              type="text"
              className="form-control mt-1"
              placeholder="Enter Description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />
          </div>
          <div className="form-group mt-3">
            <label>Occupation</label>
            <input
              type="text"
              className="form-control mt-1"
              placeholder="Enter Occupation"
              value={occupation}
              onChange={(e) => setOccupation(e.target.value)}
            />
          </div>
          <div class="form-group mt-3">
            <label>Username</label>
            <input
              type="text"
              className="form-control mt-1"
              placeholder="Enter Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div class="form-group mt-3">
            <label>Password</label>
            <input
              type="password"
              className="form-control mt-1"
              placeholder="Enter password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <div className="d-grid gap-2 mt-3">
            <Button type="submit" variant="primary">
              Submit
            </Button>
          </div>
          <p className="register-link text-right mt-2">
            <a href="/">Login</a>
          </p>
        </div>
      </form>
    </div>
  );
}

export default Signup;
