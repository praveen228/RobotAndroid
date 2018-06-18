package com.example.praveen.robotmotioncontrolv2;

/**
 * Created by PRAVEEN on 06-05-2018.
 */

public class Datapacket {
    private String syncBits = "*/";
    private String separator = "*";
    private String direction;
    private String speed;
    private String packet;

    public Datapacket(String direction, String speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public byte[] getPacket() {
        packet = syncBits + separator + direction + separator + speed + syncBits;
        return packet.getBytes();
    }
}
