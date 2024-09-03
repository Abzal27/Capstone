<template>
  <div>
    <div v-if="showConfirmationModal" class="confirmation-modal">
      <p class="message">Are you sure you want to sign out?</p>
      <button class="yes-button" @click="confirmSignOut">Yes</button>
      <button class="no-button" @click="cancelSignOut">No</button>
    </div>
    <div v-if="successModal.show" class="confirmation-modal">
      {{ successModal.message }}
      <button @click="closeModal">OK</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      showConfirmationModal: true,
      successModal: {
        show: false,
        message: "You have successfully signed out.",
      },
    };
  },
  methods: {
    confirmSignOut() {
      localStorage.removeItem('username'); // Clear the username from localStorage
      localStorage.removeItem('token');    // Clear the token from localStorage
      this.showConfirmationModal = false;
      this.successModal.show = true;
    },
    cancelSignOut() {
      this.showConfirmationModal = false;
      this.$router.push('/'); // Redirect to the home page or previous page
    },
    closeModal() {
      this.successModal.show = false;
      this.$router.push('/login'); // Redirect to login page or home page
    },
  },
};
</script>

<style scoped>
.background-container {
  background: white;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
}

.confirmation-modal-wrapper {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
}

.confirmation-modal {
  color: black;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: linear-gradient(135deg, #f54d4f, #4452c5);
  padding: 20px;
  border: 1px solid #ccc;
  text-align: center;
  border-radius: 5px;
}

.waving-character {
  font-size: 3em;
  position: absolute;
  top: -80px;
  left: 50%;
  transform: translateX(-50%);
  animation: wave 2s infinite;
}

@keyframes wave {
  0% { transform: translateX(-50%) rotate(0deg); }
  25% { transform: translateX(-50%) rotate(15deg); }
  50% { transform: translateX(-50%) rotate(0deg); }
  75% { transform: translateX(-50%) rotate(-15deg); }
  100% { transform: translateX(-50%) rotate(0deg); }
}

.message {
  font-size: 1.2em;
  margin-bottom: 20px;
}

.yes-button {
  background-color: #28a745;
  color: #fff;
  border: none;
  padding: 10px 20px;
  font-size: 1em;
  cursor: pointer;
  margin: 0 5px;
  border-radius: 5px;
  outline: none;
  transition: background-color 0.3s;
}

.no-button {
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 10px 20px;
  font-size: 1em;
  cursor: pointer;
  margin: 0 5px;
  border-radius: 5px;
  outline: none;
  transition: background-color 0.3s;
}

.yes-button:hover {
  background-color: #218838;
}

.no-button:hover {
  background-color: #0056b3;
}
</style>
/*
Author:Moegamat Isgak Abzal
Date:17/6/2024
*/