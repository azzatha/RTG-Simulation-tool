/*
 * Copyright (c) 2014. Real Time Genomics Limited.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the
 *    distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.rtg.util;

import java.io.File;
import java.io.IOException;

import com.rtg.util.diagnostic.ErrorType;
import com.rtg.util.diagnostic.NoTalkbackSlimException;
import com.rtg.util.io.FileUtils;

/**
 * Helper class for HTML reports generated by our modules
 */
public class HtmlReportHelper {

  /** Suffix for a report dir */
  public static final String REPORT_DIR_SUFFIX = "_files";

  private final File mOutputDir;
  private final File mReportFile;
  private final File mResourcesDir;

  /**
   * Construct an <code>HtmlReportHelper</code>
   * @param outputDir the base output directory as specified by the user
   * @param reportName the base report name (e.g. "report") - omitting any file type suffix
   */
  public HtmlReportHelper(File outputDir, String reportName) {
    mOutputDir = outputDir;
    mReportFile = new File(outputDir, reportName + ".html");
    mResourcesDir = new File(outputDir, reportName + REPORT_DIR_SUFFIX);
  }

  /**
   * @return the <code>File</code> object to write the report to
   */
  public File getReportFile() {
    return mReportFile;
  }

  /**
   * @return the base dir
   */
  public File getBaseDir() {
    return mOutputDir;
  }

  /**
   * @return the name of the directory under the output directory which will contain resources for the report
   */
  public String getResourcesDirName() {
    return mResourcesDir.getName();
  }

  /**
   * Get the resources directory, creating it if it doesn't exist
   * @return the resources directory
   */
  public File getResourcesDir() {
    if (!mResourcesDir.exists() && !mResourcesDir.mkdir()) {
      throw new NoTalkbackSlimException(ErrorType.DIRECTORY_NOT_CREATED, mResourcesDir.getPath());
    }
    return mResourcesDir;
  }

  /**
   * Copy resources to the report directory. File names are retained.
   * Creates report files directory if necessary.
   * @param resources java resource paths which should be copied to the report directory
   * @throws IOException if an exception occurs during io
   */
  public void copyResources(String... resources) throws IOException {
    getResourcesDir();  //ensure resources dir exists
    for (String resource : resources) {
      final int lastSep = resource.lastIndexOf('/');
      final String fileName;
      if (lastSep == -1) {
        fileName = resource;
      } else {
        fileName = resource.substring(lastSep);
      }
      FileUtils.copyResource(resource, new File(mResourcesDir, fileName));
    }
  }
}