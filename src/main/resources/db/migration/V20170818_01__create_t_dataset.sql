create table t_dataset (
  id varchar(32) primary key,
  name varchar(256) not null,
  description varchar(256) not null,
  delimiter varchar(32) not null,
  charset varchar(32) not null,
  dataset_type varchar(32) not null,
  file_size bigint not null,
  record_count bigint not null,
  digest varchar(128) not null,
  created_at timestamp not null,
  updated_at timestamp not null
);
