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

/**
 * This is a test set for <a href="https://issues.apache.org/jira/browse/MNG-7606">MNG-7606</a>.
 * It checks that "import" scope for dependencies work
 *
 */
class MavenITmng7606DependencyImportScopeTest extends AbstractMavenIntegrationTestCase {

    public MavenITmng7606DependencyImportScopeTest() {
        super(ALL_MAVEN_VERSIONS);
    }

    /**
     * Verify that dependencies which are managed through imported dependency management work
     *
     * @throws Exception in case of failure
     */
    @Test
    void testDependencyResolution() throws Exception {
        File testDir = extractResources("/mng-7606");

        Verifier verifier = newVerifier(testDir.getAbsolutePath());
        verifier.setAutoclean(true);
        verifier.deleteArtifacts("org.apache.maven.its.mng7606");
        verifier.addCliArgument("verify");
        verifier.execute();
        verifier.verifyErrorFreeLog();
    }
}