package org.snoflo.repository;

import java.io.IOException;
import java.util.List;

import org.snoflo.domain.Question;

public interface DataConverter {

    List<Question> convertData() throws IOException; 

    List<Question> getData();
}
