# Engineering Decisions — ObserveFlow

O ObserveFlow é deliberadamente um laboratório de observabilidade. O objetivo é deixar claro o problema técnico que cada componente resolve.

## 1. Objetivo

O projeto busca responder:
- qual serviço falhou?
- onde a latência aumentou?
- qual requisição percorreu quais serviços?
- o problema está em HTTP, banco, mensageria ou infraestrutura?
- o usuário está sendo afetado mesmo com health verde?

## 2. Métricas, logs e traces

Métricas respondem o que está acontecendo em escala.

Logs explicam eventos discretos e contexto.

Traces mostram o caminho e a distribuição de latência entre serviços.

Os três sinais são complementares.

## 3. Prometheus + Grafana

Indicadores prioritários:
- request rate;
- error rate;
- latency p95/p99;
- JVM, memória e GC;
- pool de conexões;
- dependências downstream.

Dashboards devem orientar decisões, não apenas exibir muitos gráficos.

## 4. OpenTelemetry + Tempo

Tracing distribuído permite seguir uma requisição entre serviços.

**Trade-off:** instrumentação e armazenamento têm custo; sampling e retenção precisam ser tratados quando o volume cresce.

## 5. Loki

Logs estruturados e correlacionados com trace/correlation id tornam investigação mais rápida.

Excesso de log sem estrutura aumenta custo sem melhorar diagnóstico.

## 6. SLI, SLO e alertas

A evolução deve usar indicadores orientados ao usuário, não alertas isolados de infraestrutura.

## 7. Chaos Lab

Chaos serve para validar hipóteses:
- timeout funciona?
- retry gera tempestade?
- circuit breaker reduz pressão?
- dashboard evidencia impacto?
- o sistema recupera?

## 8. Estado atual x roadmap

O repositório começou como infraestrutura/esqueleto. Logs centralizados, tracing completo, SLI/SLO, chaos e Kafka são incrementais e não devem ser apresentados como concluídos antes da implementação.

## 9. Diagnóstico de alta latência

1. Verificar Rate, Errors e Duration.
2. Isolar endpoint afetado.
3. Abrir traces mais lentos.
4. Identificar span dominante.
5. Consultar logs correlacionados.
6. Verificar banco e dependências.
7. Corrigir causa.
8. Confirmar retorno ao baseline.

## 10. Critério de sucesso

Um incidente simulado deve ser detectado por métrica, localizado por trace, explicado por log e acompanhado até a recuperação.
