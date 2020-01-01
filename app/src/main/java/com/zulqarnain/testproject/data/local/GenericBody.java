package com.zulqarnain.testproject.data.local;

import com.google.gson.reflect.TypeToken;

public final class GenericBody<T> {

    final T body;
    final TypeToken<T> typeToken;

    GenericBody(final T body, final TypeToken<T> typeToken) {
        this.body = body;
        this.typeToken = typeToken;
    }

    public T getBody() {
        return body;
    }

    public TypeToken<T> getTypeToken() {
        return typeToken;
    }
}