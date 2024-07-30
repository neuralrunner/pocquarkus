package dev.neuralkatana.qrkcmd;

import jakarta.persistence.*;

    @Entity
    @Table(name="room")
public class Room {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        @Column(name="room_id")
        private long id;

        @Column(name="name")
        private String name;

        @Column(name="room_number", columnDefinition = "bpchar(2)")
        private String roomNumber;

        @Column(name="bed_info", columnDefinition = "bpchar(2)")
        private String bedInfo;

        public Room() {
        }

        public Room(String name, String roomNumber, String bedInfo) {
            this.name = name;
            this.roomNumber = roomNumber;
            this.bedInfo = bedInfo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getBedInfo() {
            return bedInfo;
        }

        public void setBedInfo(String bedInfo) {
            this.bedInfo = bedInfo;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "name='" + name + '\'' +
                    ", roomNumber='" + roomNumber + '\'' +
                    ", bedInfo='" + bedInfo + '\'' +
                    '}';
        }
    }
