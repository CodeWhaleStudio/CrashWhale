# CrashWhale

CrashWhale is a simple debug screen handler helping catch exception for your application.

## Note

CrashWhale is inspired by [WhatTheStack](https://github.com/haroldadmin/WhatTheStack), but provides
Material 3 appearances which is suitable for a majority of applications building with Material 3 standards.

CrashWhale is implemented with Jetpack Startup by Androidx. With this startup, developers do not need to
add the initialize code for their application when attempting to use CrashWhale. All stuffs will be
running once CrashWhale is successfully imported into the application.

Notes: XML or Compose activities are both supported.

### Known issues

The debug screen may not appear if it crashes too fast.

## Get Started

CrashWhale is published into JitPack repository, please complete the following instructions in order to import
CrashWhale into your application.

Add Jitpack repository in `settings.gradle`

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency in `app/build.gradle`

```groovy
debugImplementation 'com.github.CodeWhaleOfficial:CrashWhale:$version'
```
