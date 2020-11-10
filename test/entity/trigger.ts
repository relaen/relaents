
import {TriggerEvent} from './triggerevent'
import { Entity, Id, Column, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_trigger",'tunnel')
export class Trigger extends BaseEntity{
	@Id()
	@Column({
		name:'trigger_id',
		type:'int',
		nullable:false
	})
	triggerId:number;

	@Column({
		name:'trigger_name',
		type:'string',
		nullable:true
	})
	triggerName:string;

	@Column({
		name:'instance_name',
		type:'string',
		nullable:true
	})
	instanceName:string;

	@Column({
		name:'method_name',
		type:'string',
		nullable:true
	})
	methodName:string;

	@OneToMany({entity:'TriggerEvent',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'trigger',lazyFetch:true})
	triggerEvents:Array<TriggerEvent>;

}