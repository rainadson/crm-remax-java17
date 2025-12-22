-- V1: Pessoas + Oportunidades (Kanban)
create table people (
  id bigserial primary key,
  name varchar(120) not null,
  category varchar(40) not null,
  segment varchar(1) not null,
  influence varchar(10),
  potential varchar(30),
  whatsapp varchar(30),
  email varchar(120),
  city varchar(80),
  region varchar(120),
  last_contact date,
  last_contact_time time,
  next_contact date,
  next_contact_time time,
  notes text,
  created_at timestamp not null default now()
);

create table opportunities (
  id bigserial primary key,
  person_id bigint references people(id) on delete set null,
  type varchar(20) not null,
  stage varchar(20) not null,
  position int not null default 0,
  title varchar(160),
  region varchar(120),
  estimated_value numeric(14,2),
  next_action varchar(30),
  next_date date,
  status varchar(10) not null default 'ABERTO',
  created_at timestamp not null default now()
);

create index idx_opportunities_stage_position on opportunities(stage, position);
create index idx_people_name on people(name);
