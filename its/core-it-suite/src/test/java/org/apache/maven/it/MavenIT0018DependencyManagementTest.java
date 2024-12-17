/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.maven.it;

import java.io.File;

import org.junit.jupiter.api.Test;

public class MavenIT0018DependencyManagementTest extends AbstractMavenIntegrationTestCase {
    public MavenIT0018DependencyManagementTest() {
        super(ALL_MAVEN_VERSIONS);
    }

    /**
     * Ensure that managed dependencies for dependency POMs are calculated
     * correctly when resolved. Removes managed-dep-1.0.3 and checks it is
     * redownloaded upon resolution of direct-dep.
     *
     * @throws Exception in case of failure
     */
    @Test
    public void testit0018() throws Exception {
        File testDir = extractResources("/it0018");
        Verifier verifier = newVerifier(testDir.getAbsolutePath());
        verifier.setAutoclean(false);
        verifier.deleteArtifacts("org.apache.maven.its.it0018");
        verifier.filterFile("settings-template.xml", "settings.xml");
        verifier.addCliArgument("--settings");
        verifier.addCliArgument("settings.xml");
        verifier.addCliArgument(
                "org.apache.maven.its.plugins:maven-it-plugin-dependency-resolution:2.1-SNAPSHOT:compile");
        verifier.execute();
        verifier.verifyArtifactPresent("org.apache.maven.its.it0018", "managed-dep", "1.0.3", "jar");
        verifier.verifyErrorFreeLog();
    }
}
