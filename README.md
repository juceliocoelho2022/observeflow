# ObserveFlow

Plataforma de laboratório e portfólio para observabilidade de microsserviços Java.

## Stack inicial
- Java 21 + Spring Boot
- Spring Boot Actuator + Micrometer
- Prometheus + Grafana
- Loki
- OpenTelemetry + Tempo
- PostgreSQL 17
- Docker Compose

## Microsserviços
- API Gateway — 8080
- Payment Service — 8081
- Fraud Service — 8082
- Ledger Service — 8083
- User Service — 8084

## Fase atual — v0.1
Esta primeira entrega cria o esqueleto arquitetural e a infraestrutura de observabilidade.
Os fluxos de negócio, persistência, logs centralizados, dashboards, alertas, SLI/SLO e Chaos Lab
serão implementados incrementalmente.

## Como iniciar
1. Compile os serviços:
   `mvn clean package -DskipTests`
2. Suba a stack:
   `docker compose up -d --build`
3. Grafana: http://localhost:3000 (admin/admin)
4. Prometheus: http://localhost:9090

## Roadmap
- v0.2: fluxo real Payment -> Fraud -> Ledger
- v0.3: PostgreSQL + Flyway + HikariCP
- v0.4: logs JSON + Loki
- v0.5: distributed tracing ponta a ponta
- v0.6: dashboards Grafana
- v0.7: alertas + SLI/SLO
- v0.8: Chaos Lab
- v0.9: Kafka
- v1.0: Kubernetes/EKS + documentação de produção
