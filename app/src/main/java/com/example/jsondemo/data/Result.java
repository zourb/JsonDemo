package com.example.jsondemo.data;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

@JsonAdapter(ResultJsonAdapter.class)
public class Result<T extends IData> implements IData<T> {
    public int errno;
    public String errmsg;
    public T data;

    final Class<T> myType;


    public Result() {
        this.errno = -1;
        this.errmsg = "";
        this.myType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        try {
            this.data = myType.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(JsonReader reader) throws IOException, IllegalStateException {
        reader.beginObject();
        while (reader.hasNext()) {
            String strName = reader.nextName();
            if ("errno".equals(strName)) {
                try {
                    this.errno = reader.nextInt();
                } catch (NumberFormatException e) {
                    reader.skipValue();
                    errno = -1;
                }

            } else if ("errmsg".equals(strName)) {
                this.errmsg = reader.nextString();
            } else if ("data".equals(strName)) {
                // How to new instance?
                this.data.read(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
    }
}
