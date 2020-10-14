package ru.unclediga.saburov.city.domain;

public class PersonResponse {
    private boolean registered;
    private boolean temporal;

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isTemporal() {
        return temporal;
    }

    public void setTemporal(boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public String toString() {
        return "PersonCheckDao{" +
                "registered=" + registered +
                ", temporal=" + temporal +
                '}';
    }
}
