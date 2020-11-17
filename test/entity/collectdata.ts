import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {CollectdataType} from './collectdatatype'
import {TriggerLog} from './triggerlog'

@Entity("t_collect_data",'tunnel')
export class CollectData extends BaseEntity{
	@Id()
	@Column({
		name:'collect_data_id',
		type:'int',
		nullable:false
	})
	private collectDataId:number;

	@ManyToOne({entity:'CollectdataType',eager:false})
	@JoinColumn({name:'collectdata_type_id',refName:'collectdata_type_id'})
	private collectdataType:CollectdataType;

	@Column({
		name:'collect_time',
		type:'int',
		nullable:true
	})
	private collectTime:number;

	@Column({
		name:'collect_value',
		type:'double',
		nullable:true
	})
	private collectValue:number;

	@Column({
		name:'is_error',
		type:'int',
		nullable:true
	})
	private isError:number;

	@OneToMany({entity:'TriggerLog',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'collectData',eager:false})
	private triggerLogs:Array<TriggerLog>;

	public getCollectDataId():number{
		return this.collectDataId;
	}
	public setCollectDataId(value:number){
		this.collectDataId = value;
	}

	public getCollectdataType():CollectdataType{
		return this.collectdataType;
	}
	public setCollectdataType(value:CollectdataType){
		this.collectdataType = value;
	}

	public getCollectTime():number{
		return this.collectTime;
	}
	public setCollectTime(value:number){
		this.collectTime = value;
	}

	public getCollectValue():number{
		return this.collectValue;
	}
	public setCollectValue(value:number){
		this.collectValue = value;
	}

	public getIsError():number{
		return this.isError;
	}
	public setIsError(value:number){
		this.isError = value;
	}

	public getTriggerLogs():Array<TriggerLog>{
		return this.triggerLogs;
	}
	public setTriggerLogs(value:Array<TriggerLog>){
		this.triggerLogs = value;
	}

}