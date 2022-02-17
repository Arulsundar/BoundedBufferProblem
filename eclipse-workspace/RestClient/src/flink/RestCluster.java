package flink;

import org.apache.flink.client.deployment.StandaloneClusterId;
import org.apache.flink.client.program.PackagedProgram;
import org.apache.flink.client.program.rest.RestClusterClient;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.JobManagerOptions;
import org.apache.flink.configuration.RestOptions;

import java.io.File;

public class RestCluster {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration();
        config.setString(JobManagerOptions.ADDRESS, "localhost");
        config.setInteger(RestOptions.PORT, 8081);

        File jarFile = new File("C:\\flink\\examples\\streaming\\WordCount.jar");

        PackagedProgram packagedProgram = new PackagedProgram(jarFile);

        RestClusterClient<StandaloneClusterId> client =
                new RestClusterClient<StandaloneClusterId>(config, StandaloneClusterId.getInstance());

        client.run(packagedProgram, 1);
    }
}
