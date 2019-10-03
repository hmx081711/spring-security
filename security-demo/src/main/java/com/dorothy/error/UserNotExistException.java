package com.dorothy.error;

public class UserNotExistException extends Throwable {

    private Long id;

    public UserNotExistException(Long id,String message) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
