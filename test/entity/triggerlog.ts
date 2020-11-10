
import {TriggerEvent} from './triggerevent'
import {CollectData} from './collectdata'
import { Entity, Id, Column, ManyToOne, JoinColumn } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';

@Entity("t_trigger_log",'tunnel')
export class TriggerLog extends BaseEntity{
	@Id()
	@Column({
		name:'trigger_log_id',
		type:'int',
		nullable:false
	})
	triggerLogId:number;

	@ManyToOne({entity:TriggerLog,lazyFetch:true})
	@JoinColumn({name:'trigger_event_id',refName:'trigger_event_id'})
	triggerEvent:TriggerEvent;

	@ManyToOne({entity:TriggerLog,lazyFetch:true})
	@JoinColumn({name:'collect_data_id',refName:'collect_data_id'})
	collectData:CollectData;

	@Column({
		name:'trigger_time',
		type:'int',
		nullable:true
	})
	triggerTime:number;

}