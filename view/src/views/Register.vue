<template>
  <div class="background-container">
    <div class="card-container">
      <div class="form-container" @submit.prevent="register">
        <form>
          <h2 class="form-header"><i class="fas fa-user-plus"></i> Signup</h2>

          <div class="form-group">
            <label for="firstName"><i class="fas fa-user"></i> First Name:</label>
            <input type="text" id="firstName" name="firstName" placeholder="Enter your first name" v-model="firstName" required>
          </div>
          <div class="form-group">
            <label for="lastName"><i class="fas fa-user"></i> Last Name:</label>
            <input type="text" id="lastName" name="lastName" placeholder="Enter your last name" v-model="lastName" required>
          </div>
          <div class="form-group">
            <label for="email"><i class="fas fa-envelope"></i> Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" v-model="email" required>
          </div>
          <div class="form-group">
            <label for="phoneNumber"><i class="fas fa-phone"></i> Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" placeholder="Enter your phone number" v-model="phoneNumber">
          </div>
          <div class="form-group">
            <label for="userName"><i class="fas fa-user-circle"></i> Username:</label>
            <input type="text" id="userName" name="userName" placeholder="Enter your username" v-model="userName" required>
          </div>
          <div class="form-group">
            <label for="password"><i class="fas fa-lock"></i> Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" v-model="password" required>
          </div>
          <div class="form-group">
            <label for="confirmPassword"><i class="fas fa-lock"></i> Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password" required>
          </div>
          <div class="button-container">
            <button class="signup-button button" type="submit"><i class="fas fa-user-plus"></i> Signup</button>
            <button class="login-button button" @click="goToLogin"><i class="fas fa-sign-in-alt"></i> Login</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      phoneNumber: "",
      userName: "",
      password: "",
      loadingModal: false,
      successModal: {
        show: false,
        message: ""
      },
      failureModal: {
        show: false,
        message: ""
      }
    };
  },
  methods: {
    register() {
      this.loadingModal = true;
      axios
          .post("http://localhost:8080/api/user/register", {
            userName: this.userName,
            firstName: this.firstName,
            lastName: this.lastName,
            phoneNumber: this.phoneNumber,
            password: this.password,
            email: this.email
          })
          .then(() => {
            this.loadingModal = false;
            alert("Registration successful");
          })
          .catch(() => {
            this.loadingModal = false;
            alert("Registration failed");
          });
    },
    goToLogin() {

      this.$router.push("/login");
    }
  }
};
</script>

<style scoped>
.background-container {
  background: linear-gradient(135deg, #f54d4f, #4452c5);
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
}

.card-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 500px;
}

.form-container {
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 400px;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-header {
  text-align: center;
  margin-bottom: 20px;
  font-size: 24px;
  color: #333;
}

.form-group {
  margin-bottom: 15px;
  width: 100%;
}

.form-group label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
  color: #555;
}

.form-group input {
  width: calc(100% - 22px);
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
  margin: 0 auto;
}

.button-container {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.button {
  border: none;
  padding: 10px 20px;
  font-size: 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s;
}

.signup-button {
  background-color: #28a745;
  color: #fff;
}

.signup-button:hover {
  background-color: #218838;
}

.login-button {
  background-color: #007bff;
  color: #fff;
}

.login-button:hover {
  background-color: #0056b3;
}
</style>
/*
Author:Moegamat Isgak Abzal
Date:25/5/2024
*/