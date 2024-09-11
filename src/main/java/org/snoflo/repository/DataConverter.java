package org.snoflo.repository;

import java.io.IOException;
import java.util.List;

public interface DataConverter<T> {

    List<T> convertData() throws IOException; 

    List<T> getData();
}
