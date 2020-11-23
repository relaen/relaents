import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {CollectData} from './collectdata'
import {TriggerEvent} from './triggerevent'

@Entity("t_collectdata_type",'tunnel')
export class CollectdataType extends BaseEntity{
	@Id()
	@Column({
		name:'collectdata_type_id',
		type:'int',
		nullable:false
	})
	private collectdataTypeId:number;

	@Column({
		name:'type_name',
		type:'string',
		nullable:true
	})
	private typeName:string;

	@Column({
		name:'simple_name',
		type:'string',
		nullable:true
	})
	private simpleName:string;

	@Column({
		name:'valid_min',
		type:'double',
		nullable:true
	})
	private validMin:number;

	@Column({
		name:'valid_max',
		type:'double',
		nullable:true
	})
	private validMax:number;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true
	})
	private remarks:string;

	@OneToMany({entity:'CollectData',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'collectdataType',eager:false})
	private collectDatas:Array<CollectData>;

	@OneToMany({entity:'TriggerEvent',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'collectdataType',eager:false})
	private triggerEvents:Array<TriggerEvent>;

	public getCollectdataTypeId():number{
		return this.collectdataTypeId;
	}
	public setCollectdataTypeId(value:number){
		this.collectdataTypeId = value;
	}

	public getTypeName():string{
		return this.typeName;
	}
	public setTypeName(value:string){
		this.typeName = value;
	}

	public getSimpleName():string{
		return this.simpleName;
	}
	public setSimpleName(value:string){
		this.simpleName = value;
	}

	public getValidMin():number{
		return this.validMin;
	}
	public setValidMin(value:number){
		this.validMin = value;
	}

	public getValidMax():number{
		return this.validMax;
	}
	public setValidMax(value:number){
		this.validMax = value;
	}

	public getRemarks():string{
		return this.remarks;
	}
	public setRemarks(value:string){
		this.remarks = value;
	}

	public getCollectDatas():Array<CollectData>{
		return this.collectDatas;
	}
	public setCollectDatas(value:Array<CollectData>){
		this.collectDatas = value;
	}

	public getTriggerEvents():Array<TriggerEvent>{
		return this.triggerEvents;
	}
	public setTriggerEvents(value:Array<TriggerEvent>){
		this.triggerEvents = value;
	}

}