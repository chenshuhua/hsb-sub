-- Create table
create table SUB_UPDATE_LOG
(
  id               NUMBER not null,
  program_version  VARCHAR2(15),
  program_file_uri VARCHAR2(300),
  modify_time      DATE,
  modifier_id      VARCHAR2(50),
  is_active        NUMBER(1) default 1,
  log_md           CLOB,
  log_html         CLOB
)
tablespace TBS_HIP_ESB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column SUB_UPDATE_LOG.id
  is 'id，主键';
comment on column SUB_UPDATE_LOG.program_version
  is '程序版本编号';
comment on column SUB_UPDATE_LOG.program_file_uri
  is '程序文件地址';
comment on column SUB_UPDATE_LOG.modify_time
  is '最后构建时间';
comment on column SUB_UPDATE_LOG.modifier_id
  is '修改人标识';
comment on column SUB_UPDATE_LOG.is_active
  is '是否有效（1 有效， 0 无效）';
comment on column SUB_UPDATE_LOG.log_md
  is '升级日志内容（Markdown数据）';
comment on column SUB_UPDATE_LOG.log_html
  is '升级日志内容（HTML数据）';




-- Create sequence 
create sequence SEQ_SUB_ID
minvalue 10002
maxvalue 999999999999999999999999999
start with 10052
increment by 1
cache 20;
