<template>
    <loading-modal v-if="loadingModal" show/>
    <div class ="card-container">
        <div class="form-container" @submit.prevent="contact">
            <form>
                <h2 class="form-header"> <i class="fas fa-envelope" ></i> Contact Us</h2>

                <div class="form-group">
                    <label for="title"><i class="fas fa-user"></i> Title:</label>
                    <input type="text" id="title" name="userName" placeholder="Enter your title" v-model="title" required>
                </div>

                <div class="form-group">
                    <label for="first-name"><i class="fas fa-user"></i> First Name:</label>
                    <input type="text" id="firstName" name="firstName" placeholder="Enter your first name" v-model="firstName" required>
                </div>

                <div class="form-group">
                    <label for="last-name"><i class="fas fa-user"></i> Last Name:</label>
                    <input type="text" id="lastName" name="lastName" placeholder="Enter your last name" v-model="lastName" required>
                </div>

                <div class="form-group">
                    <label for="email"><i class="fas fa-envelope"></i> Email:</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email" v-model="email" required>
                </div>

                <div class="form-group">
                    <label for="subject-query"><i class="fas fa-user"></i> Subject of Query:</label>
                    <input type="text" id="subject" name="subject" placeholder="Enter subject here" v-model="subject" required>
                </div>

                <div class="form-group">
                    <label for="message"><i class="fas fa-envelope"></i> Message:</label>
                    <input type="text" id="message" name="message" placeholder="Enter your query here" v-model="message" required>
                </div>

                <div class="button-container">
                    <button class="add-button button"   type="submit" ><i class="fa fa-paper-plane" aria-hidden="true"></i> Submit</button>
                </div>

            </form>
        </div>
    </div>
    <div class="modal-body">
        <success-modal :show="successModal.show" @close="closeModal" :message="successModal.message" />
        <failure-modal :show="failureModal.show" @close="closeModal" :message="failureModal.message" />
    </div>
</template>

<script>
import axios from "axios";
//import LoadingModal from "@/components/Main/Modals/LoadingModal.vue";
import loadingModal from "@/views/Modals/LoadingModal.vue";
import FailureModal from "@/views/Modals/FailureModal.vue";
import SuccessModal from "@/views/Modals/SuccessModal.vue";
import LoadingModal from "@/views/Modals/LoadingModal.vue";
import ConfirmationModal from "@/views/Modals/ConfirmationModal.vue";

export default {
    computed: {
    },
    components: {
        LoadingModal,
        SuccessModal,
        FailureModal,
        ConfirmationModal,
    },
    data() {
        return {
            title: "",
            firstName: "",
            lastName: "",
            email: "",
            subject: "",
            message: "",
            successMessage: "",
            failureMessage: "",
            errorMessage: '',
            loadingModal: false,
            successModal: {show:false, message: ""},
            failureModal: {show:false, message: ""},
            confirmationModal: {show:false, message: ""},

        };
    },
    methods: {
        contact(){
            this.loadingModal= true;
            axios
                .post("http://localhost:8080/api/contactUs/create", {
                    title: this.title,
                    firstName: this.firstName,
                    lastName: this.lastName,
                    email: this.email,
                    subject: this.email,
                    message: this.message
            })
                .then(response => {
                    // Handle success
                    console.log("Message sent successfully.");
                    console.log(response);
                    this.loadingModal = false;
                    this.successModal.message = "Message sent successfully.";
                    this.successModal.show = true;
                })
                .catch(error => {
                    // Handle error
                    this.loadingModal= false;
                    console.log("An error occurred: message not sent");
                    console.log(error);
                    this.successModal.show = false;
                    this.failureModal.show = true;
                    this.failureModal.message = "Message not sent";
                });
        },
        closeModal() {
            this.showConfirmationModal = false;
            this.successModal.show = false;
            this.failureModal.show = false;
        },
    }
};
</script>

<style>
body {
  background: linear-gradient(135deg, #f54d4f, #4452c5);
}

.card-container {
  width: 700px;
  margin: 0 auto;
  background-color: #fdfefe; /* Soft White */
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.form-header {
  text-align: center;
  margin-bottom: 20px;
  color: #4452c5; /* Blue for header */
  font-size: 1.8em;
  font-weight: bold;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  color: #4452c5; /* Blue for labels */
  font-weight: bold;
}

.form-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #fdfefe; /* Soft White */
  color: #2c3e50; /* Navy Blue */
  font-size: 1em;
}

.button-container {
  text-align: center;
  margin-top: 20px;
}

.add-button {
  background-color: #4452c5; /* Blue for buttons */
  color: #fdfefe; /* Soft White */
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  font-size: 1.2em;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.add-button:hover {
  background-color: #334199; /* Slightly darker blue */
}

.modal-body {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.5);
}
</style>