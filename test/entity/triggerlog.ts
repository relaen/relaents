import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {TriggerEvent} from './triggerevent'
import {CollectData} from './collectdata'

@Entity("t_trigger_log",'tunnel')
export class TriggerLog extends BaseEntity{
	@Id()
	@Column({
		name:'trigger_log_id',
		type:'int',
		nullable:false
	})
	private triggerLogId:number;

	@ManyToOne({entity:'TriggerEvent',eager:false})
	@JoinColumn({name:'trigger_event_id',refName:'trigger_event_id'})
	private triggerEvent:TriggerEvent;

	@ManyToOne({entity:'CollectData',eager:false})
	@JoinColumn({name:'collect_data_id',refName:'collect_data_id'})
	private collectData:CollectData;

	@Column({
		name:'trigger_time',
		type:'int',
		nullable:true
	})
	private triggerTime:number;

	public getTriggerLogId():number{
		return this.triggerLogId;
	}
	public setTriggerLogId(value:number){
		this.triggerLogId = value;
	}

	public getTriggerEvent():TriggerEvent{
		return this.triggerEvent;
	}
	public setTriggerEvent(value:TriggerEvent){
		this.triggerEvent = value;
	}

	public getCollectData():CollectData{
		return this.collectData;
	}
	public setCollectData(value:CollectData){
		this.collectData = value;
	}

	public getTriggerTime():number{
		return this.triggerTime;
	}
	public setTriggerTime(value:number){
		this.triggerTime = value;
	}

}