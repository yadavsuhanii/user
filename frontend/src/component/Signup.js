import React, { useState } from "react";
import axios from "axios";
import { Avatar, Button, Grid, Paper, TextField, Typography } from "@mui/material";

const Signup = () => {
  const paperStyle = { padding: '30px 20px', width: 300, margin: '20px auto' };
  const avatarStyle = { backgroundColor: 'green', marginTop: '10px' };
  const buttonStyle = { marginTop: '10px' };
  const textfieldStyle = { marginTop: '10px' };

  const [form, setForm] = useState({
    name: "",
    userName: "",
    email: "",
    password: ""
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      console.log(typeof form);
      const response = await axios.post('http://localhost:8080/api/auth/signup', form, {headers:{
        Accept: 'application/json',
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',

      }});
      console.log(response.data);
      // Reset the form after successful submission
      setForm({
        name: "",
        userName: "",
        email: "",
        password: ""
      });
    } catch (error) {
      console.log("Error:", error);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setForm(prevForm => ({ ...prevForm, [name]: value }));
  };

  return (
    <Grid>
      <Paper elevation={20} style={paperStyle}>
        <Grid align='center'>
          <Avatar style={avatarStyle} />
          <h2>SignUp</h2>
          <Typography gutterBottom>Please fill the form to create an account</Typography>
        </Grid>
        <form onSubmit={handleSubmit}>
          <TextField
            style={textfieldStyle}
            name="name"
            value={form.name}
            fullWidth
            label="Name"
            variant="standard"
            onChange={handleInputChange}
          />

          <TextField
            style={textfieldStyle}
            name="userName"
            value={form.username}
            fullWidth
            label="UserName"
            variant="standard"
            onChange={handleInputChange}
          />

          <TextField
            style={textfieldStyle}
            name="email"
            value={form.email}
            fullWidth
            label="Email"
            variant="standard"
            type="email"
            onChange={handleInputChange}
          />

          <TextField
            style={textfieldStyle}
            name="password"
            value={form.password}
            fullWidth
            label="Password"
            variant="standard"
            type="password"
            onChange={handleInputChange}
          />

          <Button
            type="submit"
            variant="contained"
            color="primary"
            style={buttonStyle}
          >
            SignUp
          </Button>
        </form>
      </Paper>
    </Grid>
  );
};

export default Signup;