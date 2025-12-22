# CRM RE/MAX — Java 17 (MVP)

## Pré-requisitos
- Java 17
- Maven
- Docker + Docker Compose

## Subir o banco
```bash
docker compose up -d
```

## Rodar o app
```bash
mvn spring-boot:run
```

## Abrir no navegador
- Kanban: http://localhost:8080/kanban
- Pessoas: http://localhost:8080/people

## Próximos passos (fase 2)
- CRUD de Oportunidades + reordenação (position) e drag&drop
- Tarefas e Interações
- Importação do Excel/CSV para o banco
- Login (Spring Security)
