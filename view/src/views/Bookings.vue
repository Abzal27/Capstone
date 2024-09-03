<template>
  <div class="card-container card-container-admin">
    <div class="form-container-admin">
      <router-view></router-view>

      <form ref="bookingForm" @submit.prevent="createBooking">
        <div class="form-header">
          <h2>Create Booking</h2>
        </div>

        <div class="form-group">
          <label for="user">User:</label>
          <select id="user" v-model="selectedUser" name="userId">
            <option v-for="user in users" :key="user.id" :value="user.id">
              User ID: {{ user.id }} Email: {{ user.email }}
            </option>
          </select>
        </div>

        <div class="form-group">
          <label for="issuedDate">In Date:</label>
          <input id="issuedDate" v-model="selectedIssuedDate" name="issuedDate" type="datetime-local">
        </div>

        <div class="form-group">
          <label for="returnedDate">Out Date:</label>
          <input id="returnedDate" v-model="selectedReturnedDate" name="returnedDate" type="datetime-local">
        </div>

        <div class="form-group">
          <label for="roomType">Room Type:</label>
          <select id="roomType" v-model="selectedRoomType" name="roomTypeId">
            <option v-for="roomtype in roomTypes" :key="roomtype.id" :value="roomtype.id">
              Type: {{ roomtype.type }} (ID: {{ roomtype.id }})
            </option>
          </select>
        </div>

        <div class="form-group">
          <div class="button-container">
            <button class="confirm-button button" type="submit"><i class="fas fa-check"></i> Confirm</button>
          </div>
        </div>
      </form>
    </div>
  </div>

  <div class="modal-body">
    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      users: [],
      roomTypes: [],
      selectedUser: "",
      selectedRoomType: "",
      selectedIssuedDate: "",
      selectedReturnedDate: "",
      errorMessage: "",
    };
  },
  mounted() {
    this.fetchUsersList();
    this.fetchRoomTypesList();
  },
  methods: {
    fetchUsersList() {
      axios
          .get("http://localhost:8080/api/admin/users/list/all")
          .then((response) => {
            this.users = response.data;
          })
          .catch((error) => {
            console.log(error);
            this.errorMessage = "Failed to fetch users list";
          });
    },

    fetchRoomTypesList() {
      axios
          .get("http://localhost:8080/api/roomtypes")
          .then((response) => {
            this.roomTypes = response.data;
          })
          .catch((error) => {
            console.log(error);
            this.errorMessage = "Failed to fetch room types list";
          });
    },

    createBooking() {
      console.log("Selected Room Type ID:", this.selectedRoomType); // Debugging line
      this.errorMessage = "";

      // Ensure selectedRoomType is a number
      const roomTypeId = parseInt(this.selectedRoomType, 10);
      if (isNaN(roomTypeId)) {
        this.errorMessage = "Invalid room type selected.";
        return;
      }

      const booking = {
        user: {
          id: this.selectedUser,
        },
        room: {
          id: roomTypeId,
        },
        inDate: this.selectedIssuedDate ? new Date(this.selectedIssuedDate).toISOString() : null,
        outDate: this.selectedReturnedDate ? new Date(this.selectedReturnedDate).toISOString() : null,
      };

      console.log("Booking Payload:", booking);

      axios
          .post("http://localhost:8080/api/bookings/create", booking)
          .then((response) => {
            if (response && response.data) {
              console.log("Booking created successfully");
            } else {
              console.error("Response or response.data is undefined");
            }
          })
          .catch((error) => {
            console.log(error);
            this.errorMessage = error.response ? error.response.data : "An error occurred";
          });
    },
  },
};

</script>

<style scoped>


.card-container {
  background: white;
  max-width: 400px;
  margin: 2px auto;
  height: 600px;
  padding: 40px;
  border-radius: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}


.form-container-admin {
  display: flex;
  flex-direction: column;
  gap: 20px;
}


.form-header {
  text-align: center;
  margin-bottom: 20px;
}

.form-header h2 {
  margin: 0;
  font-size: 1.8em;
  color: #4c61af;
}


.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #555;
}

.form-group input,
.form-group select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1em;
  color: #333;
  transition: border-color 0.3s ease;
}

.form-group input:focus,
.form-group select:focus {
  border-color: #007bff;
  outline: none;
}


.button-container {
  text-align: center;
}

.confirm-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 12px 20px;
  font-size: 1em;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.confirm-button:hover {
  background-color: #0056b3;
}

.confirm-button i {
  margin-right: 5px;
}


.error-message {
  color: #d9534f;
  font-weight: bold;
  text-align: center;
  margin-top: 10px;
}

body {
  margin: 0;
  font-family: 'Arial', sans-serif;
  background: white;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>
/*
Author:Moegamat Isgak Abzal
Date:17/6/2024
*/