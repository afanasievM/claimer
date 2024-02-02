package extentions

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project

internal val Project.libraries: LibrariesForLibs
    get() = extensions.getByName("libs") as LibrariesForLibs
