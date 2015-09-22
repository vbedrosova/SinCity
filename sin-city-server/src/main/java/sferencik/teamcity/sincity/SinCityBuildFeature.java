package sferencik.teamcity.sincity;

import jetbrains.buildServer.serverSide.BuildFeature;
import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SinCityBuildFeature extends BuildFeature {
    private final PluginDescriptor descriptor;

    public SinCityBuildFeature(@NotNull final PluginDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @NotNull
    @Override
    public String getType() {
        return "SinCity";
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Culprit finder";
    }

    @Nullable
    @Override
    public String getEditParametersUrl() {
        return descriptor.getPluginResourcesPath("feature.jsp");
    }

    @NotNull
    @Override
    public String describeParameters(@NotNull Map<String, String> params)
    {
        return "Find the culprit of a broken build";

    }

    @Nullable
    @Override
    public PropertiesProcessor getParametersProcessor()
    {
        return new PropertiesProcessor()
        {
            @NotNull
            public Collection<InvalidProperty> process(@Nullable final Map<String, String> propertiesMap)
            {
                // anything goes
                return new ArrayList<InvalidProperty>();
            }
        };
    }

    @Nullable
    @Override
    public Map<String, String> getDefaultParameters()
    {
        return new HashMap<String, String>();
    }

    @Override
    public boolean isMultipleFeaturesPerBuildTypeAllowed() {
        return false;
    }
}
