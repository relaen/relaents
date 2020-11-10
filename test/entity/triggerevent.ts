
import {Trigger} from './trigger'
import {CollectdataType} from './collectdatatype'
import {TriggerLog} from './triggerlog'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_trigger_event",'tunnel')
export class TriggerEvent extends BaseEntity{
	@Id()
	@Column({
		name:'trigger_event_id',
		type:'int',
		nullable:false
	})
	triggerEventId:number;

	@ManyToOne({entity:TriggerEvent,lazyFetch:true})
	@JoinColumn({name:'trigger_id',refName:'trigger_id'})
	trigger:Trigger;

	@ManyToOne({entity:TriggerEvent,lazyFetch:true})
	@JoinColumn({name:'collectdata_type_id',refName:'collectdata_type_id'})
	collectdataType:CollectdataType;

	@Column({
		name:'trigger_cond',
		type:'int',
		nullable:true
	})
	triggerCond:number;

	@OneToMany({entity:'TriggerLog',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'triggerEvent',lazyFetch:true})
	triggerLogs:Array<TriggerLog>;

}