import java.lang.reflect.Field
import jenkins.metrics.api.MetricsAccessKey
import jenkins.model.Jenkins
// Sets up an access key for the metrics plugin that be used by other services

final Jenkins jenkins = Jenkins.instance

final MetricsAccessKey.DescriptorImpl metricsDescriptor = jenkins.getDescriptorByType(MetricsAccessKey.DescriptorImpl)

// Add a new access key
if (metricsDescriptor.accessKeys.find { it.description.startsWith('init.groovy.d: ') } == null) {
  final String key = MetricsAccessKey.DescriptorImpl.generateKey()
  final boolean canPing = true
  final boolean canThreadDump = false
  final boolean canHealthcheck = true
  final boolean canMetrics = true
  final String origins = '*'
  final MetricsAccessKey newAccessKey = new MetricsAccessKey(
    'init.groovy.d: setup by init script',
    key,
    canPing,
    canThreadDump,
    canHealthcheck,
    canMetrics,
    origins
  )
  // There is not a good programmatic way to set the keys.
  // So, we need to use reflection to open up the class and add the key to the 'accessKeys' field.
  // Afterwards, we need to save the descriptor so that it is persisted and restore that field to the previous state.
  final Field accessKeysField = metricsDescriptor.class.getDeclaredField('accessKeys')
  final boolean previousAccessible = accessKeysField.isAccessible()
  accessKeysField.setAccessible(true)
  final accessKeys = accessKeysField.get(metricsDescriptor) as List<MetricsAccessKey>
  accessKeys.add(newAccessKey)
  accessKeysField.setAccessible(previousAccessible)
  metricsDescriptor.save()
}
