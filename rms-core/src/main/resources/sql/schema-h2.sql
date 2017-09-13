

CREATE TABLE IF NOT EXISTS `assessment_type` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;


CREATE TABLE IF NOT EXISTS `audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_ip` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `request` varchar(255) DEFAULT NULL,
  `request_method` varchar(255) DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  `response_time` float NOT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `succeeded` bit(1) DEFAULT NULL,
  `timezone` varchar(255) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE IF NOT EXISTS `person` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `emp_no` bigint(20) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_97uev2s8n518uu8ha23j6t6s0` (`emp_no`)
);

CREATE TABLE IF NOT EXISTS `candidate` (
  `current_ctc` varchar(255) DEFAULT NULL,
  `current_designation` varchar(255) DEFAULT NULL,
  `current_firm` varchar(255) DEFAULT NULL,
  `current_location` varchar(255) DEFAULT NULL,
  `expected_ctc` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `notice_period` varchar(255) DEFAULT NULL,
  `preferred_location` varchar(255) DEFAULT NULL,
  `relevant_experience` varchar(255) DEFAULT NULL,
  `rms_id` bigint(20) DEFAULT NULL,
  `total_experience` varchar(255) DEFAULT NULL,
  `visa_status` varchar(255) DEFAULT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE KEY `UK_n3unm19v7txa3my3ay30pg9pr` (`rms_id`),
  CONSTRAINT `FK_huj5mp2r9mum09mcuxx1ojrao` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ;

CREATE TABLE `interview` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comments_for_next_level` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `interview_date` datetime DEFAULT NULL,
  `interview_mode_type` varchar(255) DEFAULT NULL,
  `interview_type` varchar(255) DEFAULT NULL,
  `next_level_recomentation` bit(1) DEFAULT NULL,
  `referral_id` bigint(20) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_ts` datetime DEFAULT NULL,
  `candidate_id` bigint(20) DEFAULT NULL,
  `interviewer_id` bigint(20) NOT NULL,
  `referral_entity_id` bigint(20) DEFAULT NULL,
  `interviews_id` bigint(20) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
);


CREATE TABLE IF NOT EXISTS `interview_assessment` (
  `id` bigint(20) NOT NULL,
  `assessment_type` tinyblob,
  `feedback` varchar(255) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;



CREATE TABLE IF NOT EXISTS `interview_skills` (
  `interview_id` bigint(20) NOT NULL,
  `skills_id` bigint(20) NOT NULL,
  PRIMARY KEY (`interview_id`,`skills_id`),
  --UNIQUE KEY `UK_qogwdfbvppx68cp4t524bvnv9` (`skills_id`),
  --CONSTRAINT `FK_d30f1pxssf9q8idldedv9yh1g` FOREIGN KEY (`interview_id`) REFERENCES `interview` (`id`),
  CONSTRAINT `FK_qogwdfbvppx68cp4t524bvnv9` FOREIGN KEY (`skills_id`) REFERENCES `interview_assessment` (`id`)
) ;




CREATE TABLE IF NOT EXISTS `person_skills` (
  `person_id` bigint(20) NOT NULL,
  `skills_id` bigint(20) NOT NULL,
  PRIMARY KEY (`person_id`,`skills_id`),
  -- KEY `FK_ajaehknbig43qhve6pukqn5s3` (`skills_id`),
  CONSTRAINT `FK_1mhk96hk52ij5p4fchp7sge7` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  -- CONSTRAINT `FK_ajaehknbig43qhve6pukqn5s3` FOREIGN KEY (`skills_id`) REFERENCES `skill` (`id`)
) ;


CREATE TABLE IF NOT EXISTS `referral` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `additional_comments` varchar(255) DEFAULT NULL,
  `application_source` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `correspondence` varchar(255) DEFAULT NULL,
  `rms_status` varchar(255) DEFAULT NULL,
  `shared_with` varchar(255) DEFAULT NULL,
  `source_type` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `sub_status` varchar(255) DEFAULT NULL,
  `candidate_id` bigint(20) DEFAULT NULL,
  `referrer_id` bigint(20) DEFAULT NULL,
  `requisition_id` bigint(20) DEFAULT NULL,
  `wf_status_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  -- KEY `FK_3gqto138cp3lpo45r6u4g3qix` (`candidate_id`),
  -- KEY `FK_aeq9qj0j2eeocrqgm7sfisjag` (`referrer_id`),
  -- KEY `FK_lfsh7wn9u9sabu9gxqoy5l7ir` (`requisition_id`),
  -- CONSTRAINT `FK_3gqto138cp3lpo45r6u4g3qix` FOREIGN KEY (`candidate_id`) REFERENCES `person` (`id`),
  -- CONSTRAINT `FK_aeq9qj0j2eeocrqgm7sfisjag` FOREIGN KEY (`referrer_id`) REFERENCES `person` (`id`),
  -- CONSTRAINT `FK_lfsh7wn9u9sabu9gxqoy5l7ir` FOREIGN KEY (`requisition_id`) REFERENCES `requisition` (`id`)
);


CREATE TABLE IF NOT EXISTS `requisition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `function` varchar(255) DEFAULT NULL,
  `recruiter_asst_name` varchar(255) DEFAULT NULL,
  `recruiter_name` varchar(255) DEFAULT NULL,
  `requisition_no` varchar(255) DEFAULT NULL,
  `service_line` varchar(255) DEFAULT NULL,
  `speciality` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);



CREATE TABLE IF NOT EXISTS `interview` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comments_for_next_level` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `interview_date` datetime DEFAULT NULL,
  `interview_mode_type` varchar(255) DEFAULT NULL,
  `interview_type` varchar(255) DEFAULT NULL,
  `next_level_recomentation` bit(1) DEFAULT NULL,
  `referral_id` bigint(20) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_ts` datetime DEFAULT NULL,
  `candidate_id` bigint(20) DEFAULT NULL,
  `interviewer_id` bigint(20) NOT NULL,
  `referral_entity_id` bigint(20) DEFAULT NULL,
  `interviews_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
  -- KEY `FK_tqkqf1w6du1g6qcyjqskb036e` (`candidate_id`),
  -- KEY `FK_a6x8un2ugfln08beajjgpo1b3` (`interviewer_id`),
  -- KEY `FK_7cruqvkqs0d9ds6ipr2a6npsx` (`referral_entity_id`),
  -- KEY `FK_lg1405uaepwgw7ojgebcnd3ew` (`interviews_id`),
  -- CONSTRAINT `FK_7cruqvkqs0d9ds6ipr2a6npsx` FOREIGN KEY (`referral_entity_id`) REFERENCES `referral` (`id`),
  -- CONSTRAINT `FK_a6x8un2ugfln08beajjgpo1b3` FOREIGN KEY (`interviewer_id`) REFERENCES `person` (`id`),
  -- CONSTRAINT `FK_lg1405uaepwgw7ojgebcnd3ew` FOREIGN KEY (`interviews_id`) REFERENCES `referral` (`id`),
  -- CONSTRAINT `FK_tqkqf1w6du1g6qcyjqskb036e` FOREIGN KEY (`candidate_id`) REFERENCES `person` (`id`)
) ;




CREATE TABLE IF NOT EXISTS `postal_codes` (
  `pincode` bigint(11) NOT NULL,
  `po_name` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `district` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ;


CREATE TABLE IF NOT EXISTS `wf_master` (
  `id` bigint(11) NOT NULL,
  `wf_status_id` int(11) NOT NULL,
  `target_wf_status_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  -- KEY `FK_wf_master_wf_status_target_idx` (`target_wf_status_id`),
  -- KEY `FK_7uc00e6vtk1dcb52onrj0kn5b` (`wf_status_id`),
  -- CONSTRAINT `FK_7uc00e6vtk1dcb52onrj0kn5b` FOREIGN KEY (`wf_status_id`) REFERENCES `wf_status` (`id`),
  -- CONSTRAINT `FK_jnblkupmajc3ywd31oushnlkn` FOREIGN KEY (`target_wf_status_id`) REFERENCES `wf_status` (`id`),
  -- CONSTRAINT `FK_wf_master_wf_status` FOREIGN KEY (`wf_status_id`) REFERENCES `wf_status` (`id`),
  -- CONSTRAINT `FK_wf_master_wf_status_target` FOREIGN KEY (`target_wf_status_id`) REFERENCES `wf_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;




CREATE TABLE IF NOT EXISTS `wf_status` (
  `id` bigint(11) NOT NULL,
  `wf_status` varchar(255) NOT NULL,
  `wf_substatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE IF NOT EXISTS `wf_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `from_wf_status_id` bigint(20) DEFAULT NULL,
  `reference_id` bigint(20) DEFAULT NULL,
  `to_wf_status_id` bigint(20) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
);



