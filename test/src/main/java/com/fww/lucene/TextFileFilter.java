package com.fww.lucene;

import java.io.File;
import java.io.FileFilter;

/**
 * @author 范文武
 * @date 2018/05/04 14:20
 */
public class TextFileFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        return pathname.getName().toLowerCase().endsWith(".txt");
    }
}
