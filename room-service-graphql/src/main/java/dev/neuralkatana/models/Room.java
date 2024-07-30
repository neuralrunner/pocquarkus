package dev.neuralkatana.models;

public class Room {
    private String room;
    private String bedInfo;
    private String name;

    public Room() {
    }

    public Room(String room, String bedInfo, String name) {
        this.room = room;
        this.bedInfo = bedInfo;
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBedInfo() {
        return bedInfo;
    }

    public void setBedInfo(String bedInfo) {
        this.bedInfo = bedInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room='" + room + '\'' +
                ", bedInfo='" + bedInfo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
