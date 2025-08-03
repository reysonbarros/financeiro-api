{{/******************************************************************/}}
{{/* Definir as labels */}}

{{- define "app.labels" -}}
app: {{ .labels.app | quote }}
{{- end }}

{{/******************************************************************/}}

{{/* Definir os limites de recursos */}}

{{- define "app.resources" -}}
requests:
  cpu: {{ .containers.resources.requests.cpu }}
  memory: {{ .containers.resources.requests.memory }}
limits:
  cpu: {{ .containers.resources.limits.cpu }}
  memory: {{ .containers.resources.limits.memory }}
{{- end }}

{{/******************************************************************/}}

{{/* Definir as portas */}}

{{- define "app.ports" -}}
- containerPort: {{ .containers.port }}
  name: {{ .containers.name }}
  protocol: {{ .containers.protocol }}
{{- end }}

{{/******************************************************************/}}

{{/* Definir a livenessProbe */}}

{{- define "app.livenessProbe" -}}
httpGet:
  path: {{ .containers.livenessProbe.path }}
  port: {{ .containers.livenessProbe.port }}
  scheme: {{ .containers.livenessProbe.scheme }}
initialDelaySeconds: {{ .containers.livenessProbe.initialDelaySeconds }}
periodSeconds: {{ .containers.livenessProbe.periodSeconds }}
timeoutSeconds: {{ .containers.livenessProbe.timeoutSeconds }}
successThreshold: {{ .containers.livenessProbe.successThreshold }}
failureThreshold: {{ .containers.livenessProbe.failureThreshold }}
{{- end }}


{{/******************************************************************/}}

{{/* Definir a readinessProbe */}}

{{- define "app.readinessProbe" -}}
httpGet:
  path: {{ .containers.readinessProbe.path }}
  port: {{ .containers.readinessProbe.port }}
  scheme: {{ .containers.readinessProbe.scheme }}
initialDelaySeconds: {{ .containers.livenessProbe.initialDelaySeconds }}
periodSeconds: {{ .containers.livenessProbe.periodSeconds }}
timeoutSeconds: {{ .containers.livenessProbe.timeoutSeconds }}
successThreshold: {{ .containers.livenessProbe.successThreshold }}
failureThreshold: {{ .containers.livenessProbe.failureThreshold }}
{{- end }}


{{/******************************************************************/}}

{{/* Definir os secrets */}}

{{- define "secrets.financeiro-api" -}}
jwtSecretKey: {{ .data.jwtSecretKey }}
{{- end }}

{{- define "secrets.mysql-app" -}}
username: {{ .data.username }}
password: {{ .data.password }}
{{- end }}

{{/******************************************************************/}}

{{/* Definir os configmaps */}}

{{- define "configmaps.financeiro-api" -}}
SERVER_PORT: {{ .data.SERVER_PORT | quote }}
SPRING_DATASOURCE_URL: {{ .data.SPRING_DATASOURCE_URL }}
AWS_BUCKET_NAME: {{ .data.AWS_BUCKET_NAME }}
AWS_JAVA_V1_DISABLE_DEPRECATION_ANNOUNCEMENT: {{ .data.AWS_JAVA_V1_DISABLE_DEPRECATION_ANNOUNCEMENT | quote }}
MYSQL_DATABASE: {{ .data.MYSQL_DATABASE }}
LOG_LEVEL_ROOT: {{ .data.LOG_LEVEL_ROOT }}
LOG_LEVEL_SECURITY: {{ .data.LOG_LEVEL_SECURITY }}
LOG_LEVEL_APP: {{ .data.LOG_LEVEL_APP }}
JWT_ISSUER: {{ .data.JWT_ISSUER }}
JWT_TIME_ZONE: {{ .data.JWT_TIME_ZONE }}
JWT_EXPIRATION_SECONDS: {{ .data.JWT_EXPIRATION_SECONDS | quote }}

{{- end }}

{{- define "configmaps.mysql-app" -}}
init.sql: {{ .data.initSql }}
{{- end }}

{{/******************************************************************/}}
