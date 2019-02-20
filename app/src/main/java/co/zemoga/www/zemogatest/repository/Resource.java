package co.zemoga.www.zemogatest.repository;

import android.support.annotation.Nullable;

public class Resource<T> {

    public final Status status;
    public final String message;
    public final T data;

    public Resource(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String message, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, message);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }
}
