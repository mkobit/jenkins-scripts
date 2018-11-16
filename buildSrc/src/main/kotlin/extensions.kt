import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.internal.HasConvention
import org.gradle.api.tasks.GroovySourceSet
import org.gradle.api.tasks.SourceSet
import org.gradle.kotlin.dsl.withConvention

val SourceSet.groovy: SourceDirectorySet
  get() = withConvention(GroovySourceSet::class) { groovy }

