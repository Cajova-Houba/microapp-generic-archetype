# Generic microapplication archetype

Use this archetype to create a generic microapplication.

Create new application with:

```
mvn archetype:generate \
-DarchetypeGroupId=org.microapp \
-DarchetypeArtifactId=microapp-generic-archetype \
-DarchetypeVersion=0.2 \
-DgroupId=<groupId> \
-DartifactId=<artifactId> \
-Dversion=<version>
-DmicroappName=<microappName> \
```

The `microappName` property is used to differ between various microapplications.
