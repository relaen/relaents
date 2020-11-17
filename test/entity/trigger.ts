import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {TriggerEvent} from './triggerevent'

@Entity("t_trigger",'tunnel')
export class Trigger extends BaseEntity{
	@Id()
	@Column({
		name:'trigger_id',
		type:'int',
		nullable:false
	})
	private triggerId:number;

	@Column({
		name:'trigger_name',
		type:'string',
		nullable:true
	})
	private triggerName:string;

	@Column({
		name:'instance_name',
		type:'string',
		nullable:true
	})
	private instanceName:string;

	@Column({
		name:'method_name',
		type:'string',
		nullable:true
	})
	private methodName:string;

	@OneToMany({entity:'TriggerEvent',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'trigger',eager:false})
	private triggerEvents:Array<TriggerEvent>;

	public getTriggerId():number{
		return this.triggerId;
	}
	public setTriggerId(value:number){
		this.triggerId = value;
	}

	public getTriggerName():string{
		return this.triggerName;
	}
	public setTriggerName(value:string){
		this.triggerName = value;
	}

	public getInstanceName():string{
		return this.instanceName;
	}
	public setInstanceName(value:string){
		this.instanceName = value;
	}

	public getMethodName():string{
		return this.methodName;
	}
	public setMethodName(value:string){
		this.methodName = value;
	}

	public getTriggerEvents():Array<TriggerEvent>{
		return this.triggerEvents;
	}
	public setTriggerEvents(value:Array<TriggerEvent>){
		this.triggerEvents = value;
	}

}