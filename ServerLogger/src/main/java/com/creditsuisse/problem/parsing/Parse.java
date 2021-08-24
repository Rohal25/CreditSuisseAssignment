package com.creditsuisse.problem.parsing;

import com.creditsuisse.problem.metadata.LogDetails;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public interface Parse {

    HashMap<String, LogDetails> ReadFileAndInsertFileContentIntoMap(File file) throws IOException;

}
