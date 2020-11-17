import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {Trigger} from './trigger'
import {CollectdataType} from './collectdatatype'
import {TriggerLog} from './triggerlog'

@Entity("t_trigger_event",'tunnel')
export class TriggerEvent extends BaseEntity{
	@Id()
	@Column({
		name:'trigger_event_id',
		type:'int',
		nullable:false
	})
	private triggerEventId:number;

	@ManyToOne({entity:'Trigger',eager:false})
	@JoinColumn({name:'trigger_id',refName:'trigger_id'})
	private trigger:Trigger;

	@ManyToOne({entity:'CollectdataType',eager:false})
	@JoinColumn({name:'collectdata_type_id',refName:'collectdata_type_id'})
	private collectdataType:CollectdataType;

	@Column({
		name:'trigger_cond',
		type:'int',
		nullable:true
	})
	private triggerCond:number;

	@OneToMany({entity:'TriggerLog',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'triggerEvent',eager:false})
	private triggerLogs:Array<TriggerLog>;

	public getTriggerEventId():number{
		return this.triggerEventId;
	}
	public setTriggerEventId(value:number){
		this.triggerEventId = value;
	}

	public getTrigger():Trigger{
		return this.trigger;
	}
	public setTrigger(value:Trigger){
		this.trigger = value;
	}

	public getCollectdataType():CollectdataType{
		return this.collectdataType;
	}
	public setCollectdataType(value:CollectdataType){
		this.collectdataType = value;
	}

	public getTriggerCond():number{
		return this.triggerCond;
	}
	public setTriggerCond(value:number){
		this.triggerCond = value;
	}

	public getTriggerLogs():Array<TriggerLog>{
		return this.triggerLogs;
	}
	public setTriggerLogs(value:Array<TriggerLog>){
		this.triggerLogs = value;
	}

}