// Use the provided example on https://github.com/ben-manes/gradle-versions-plugin to exclude RC
ext.dependencyUpdatesResolutionStrategy = {
  componentSelection { rules ->
    rules.all { ComponentSelection selection ->
      boolean rejected = ['alpha', 'beta', 'rc', 'cr', 'm'].any { qualifier ->
        selection.candidate.version ==~ /(?i).*[.-]${qualifier}[.\d-]*/
      }
      if (rejected) {
        selection.reject('Release candidate')
      }
    }
  }
}