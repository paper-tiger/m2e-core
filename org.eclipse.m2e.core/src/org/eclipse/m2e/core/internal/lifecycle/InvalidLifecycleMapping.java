/*******************************************************************************
 * Copyright (c) 2010 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Sonatype, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.m2e.core.internal.lifecycle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;

import org.apache.maven.plugin.MojoExecution;

import org.eclipse.m2e.core.project.configurator.AbstractBuildParticipant;
import org.eclipse.m2e.core.project.configurator.AbstractProjectConfigurator;
import org.eclipse.m2e.core.project.configurator.ILifecycleMapping;
import org.eclipse.m2e.core.project.configurator.ProjectConfigurationRequest;


/**
 * Invlid lifecycle mapping provides additional information about lifecycle mapping problems.
 * 
 * @author igor
 */
public class InvalidLifecycleMapping implements ILifecycleMapping {

  public static class ProblemInfo {
    private final int line;

    private final String message;

    ProblemInfo(int line, String message) {
      this.line = line;
      this.message = message;
    }

    public int getLine() {
      return line;
    }

    public String getMessage() {
      return message;
    }
  }

  public static class MissingLifecycleExtensionPoint extends ProblemInfo {
    private final String lifecycleId;

    MissingLifecycleExtensionPoint(int line, String message, String lifecycleId) {
      super(line, message);
      this.lifecycleId = lifecycleId;
    }

    public String getLifecycleId() {
      return lifecycleId;
    }
  }

  private List<ProblemInfo> problems = new ArrayList<ProblemInfo>();

  public String getId() {
    return "invalid";
  }

  public String getName() {
    return "invalid";
  }

  public void configure(ProjectConfigurationRequest request, IProgressMonitor monitor) {
  }

  public void unconfigure(ProjectConfigurationRequest request, IProgressMonitor monitor) {
  }

  public List<AbstractBuildParticipant> getBuildParticipants(IProgressMonitor monitor) {
    return Collections.emptyList();
  }

  public List<AbstractProjectConfigurator> getProjectConfigurators(IProgressMonitor monitor) {
    return Collections.emptyList();
  }

  public Set<AbstractProjectConfigurator> getProjectConfiguratorsForMojoExecution(MojoExecution mojoExecution,
      IProgressMonitor monitor) {
    return Collections.emptySet();
  }

  public List<MojoExecution> getNotCoveredMojoExecutions(IProgressMonitor monitor) {
    return Collections.emptyList();
  }

  public boolean isInterestingPhase(String phase) {
    return false;
  }

  /**
   * Adds new generic lifecycle mapping problem
   */
  public void addProblem(int line, String message) {
    problems.add(new ProblemInfo(line, message));
  }

  public void addMissingLifecycleExtensionPoint(int line, String message, String lifecycleId) {
    problems.add(new MissingLifecycleExtensionPoint(line, message, lifecycleId));
  }

  public List<ProblemInfo> getProblems() {
    return problems;
  }

  public boolean hasProblems() {
    return !problems.isEmpty();
  }

}