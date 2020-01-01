package com.zulqarnain.testproject.data;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.zulqarnain.testproject.data.local.GenericBody;

import java.io.IOException;

public class GenericBodyTypeAdapterFactory
        implements TypeAdapterFactory {

    private static final TypeAdapterFactory genericBodyTypeAdapterFactory = new GenericBodyTypeAdapterFactory();

    private GenericBodyTypeAdapterFactory() {
    }

   public static TypeAdapterFactory getGenericBodyTypeAdapterFactory() {
        return genericBodyTypeAdapterFactory;
    }

    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if ( !GenericBody.class.isAssignableFrom(typeToken.getRawType()) ) {
            return null;
        }
        final TypeAdapter<GenericBody<T>> genericBodyTypeAdapter = new TypeAdapter<GenericBody<T>>() {
            @Override
            public void write(final JsonWriter out, final GenericBody<T> value)
                    throws IOException {
                final T body = value.getBody();
                final TypeAdapter<T> typeAdapter = gson.getDelegateAdapter(GenericBodyTypeAdapterFactory.this, value.getTypeToken());
                typeAdapter.write(out, body);
            }

            @Override
            public GenericBody<T> read(final JsonReader in) {
                throw new UnsupportedOperationException();
            }
        };
        @SuppressWarnings("unchecked")
        final TypeAdapter<T> typeAdapter = (TypeAdapter<T>) genericBodyTypeAdapter;
        return typeAdapter;
    }

}