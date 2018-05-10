import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.internal.HasConvention
import org.gradle.api.tasks.GroovySourceSet
import org.gradle.api.tasks.SourceSet

val SourceSet.groovy: SourceDirectorySet
  get() = (this as HasConvention).convention.getPlugin(GroovySourceSet::class.java).groovy
//
